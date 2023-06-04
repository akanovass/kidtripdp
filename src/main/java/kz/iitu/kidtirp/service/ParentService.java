package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.exceptions.ObjectNotFoundException;
import kz.iitu.kidtirp.model.dto.request.*;
import kz.iitu.kidtirp.model.dto.response.ChildLocationDto;
import kz.iitu.kidtirp.model.entity.Child;
import kz.iitu.kidtirp.model.entity.ChildLocation;
import kz.iitu.kidtirp.model.entity.Parent;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import kz.iitu.kidtirp.repository.ChildLocationRepository;
import kz.iitu.kidtirp.repository.ChildRepository;
import kz.iitu.kidtirp.repository.DriverRepository;
import kz.iitu.kidtirp.repository.ParentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ParentService {

    ChildRepository childRepository;
    ChildLocationRepository childLocationRepository;
    ParentRepository parentRepository;
    DriverRepository driverRepository;
    UserService userService;

    @Transactional
    public Parent registerParent(ParentRequest request) {
        User user = userService.createUser(new SignupRequest(request.getUsername(), request.getFullName(), request.getGmail(), request.getPhoneNumber(), request.getPassword(), ERole.PARENT));
        Parent parent = new Parent();
        parent.setCvv(request.getCvv());
        parent.setNumberCard(request.getNumberCard());
        parent.setCardExpireDate(request.getCardExpireDate());
        parent.setOwnerNameCard(request.getOwnerNameCard());
        parent.setUser(user);
        return parentRepository.save(parent);
    }

    public Parent getParentByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return parentRepository.findByUser(user);
    }

    public Parent getParentById(Long id) {
        return parentRepository.findById(id).orElse(null);
    }

    public Child getChildByUser(Long userId) {
        User user = userService.getUserById(userId);
        return childRepository.findByUser(user);
    }

    public Child getChildById(Long id) {
        return childRepository.findById(id).orElse(null);
    }


    @Transactional
    public Parent updateParent(ParentRequest request) {
        Parent parent = parentRepository.findById(request.getId()).orElseThrow();
        parent.setCvv(request.getCvv());
        parent.setNumberCard(request.getNumberCard());
        parent.setCardExpireDate(request.getCardExpireDate());
        parent.setOwnerNameCard(request.getOwnerNameCard());
        SignupRequest signupRequest = new SignupRequest(parent.getUser().getId(),
                request.getUsername(), request.getFullName(), request.getGmail(), request.getPhoneNumber(), request.getPassword(), null);
        userService.updateUser(signupRequest);
        return parentRepository.save(parent);
    }

    @Transactional
    public Child updateChild(ChildRequest request) {
        Child child = childRepository.findById(request.getId()).orElseThrow();
        child.setAddress(request.getAddress());
        child.setGender(request.getGender());
        child.setBirthDate(request.getBirthDate());
        child.setClassN(request.getClassN());
        child.setMedicalConditions(request.getMedicalConditions());
        child.setPhotoUrl(request.getPhotoUrl());
        child.setSchoolName(request.getSchoolName());
        SignupRequest signupRequest = new SignupRequest(child.getUser().getId(),
                request.getUsername(), request.getFullName(), request.getGmail(), request.getPhoneNumber(), request.getPassword(), null);
        userService.updateUser(signupRequest);
        return childRepository.save(child);
    }

    @Transactional
    public Parent addChild(ChildRequest request, Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new ObjectNotFoundException("Parent not found with id = " + parentId, "404"));
        User user = userService.createUser(new SignupRequest(request.getUsername(), request.getFullName(), request.getGmail(), request.getPhoneNumber(), request.getPassword(), ERole.CHILD));
        Child child = new Child(user, request.getBirthDate(), request.getGender(), request.getAddress(), request.getSchoolName(), request.getClassN(), request.getMedicalConditions(), request.getPhotoUrl(), parent);
        child = childRepository.save(child);
        List<Child> children = new ArrayList<>();
        if (parent.getChildren() != null && !parent.getChildren().isEmpty())
            children = parent.getChildren();
        children.add(child);
        parent.setChildren(children);
        return parent;
    }

    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    public List<Child> getAllChild() {
        return childRepository.findAll();
    }

    public Parent selectDriver(Long driverId, Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new ObjectNotFoundException("Parent not found with id = " + parentId, "404"));
        return parentRepository.save(parent);
    }

    public Parent payDrive() {
        return null;
    }

    public ChildLocation updateLocation(LocationDto locationDto, Long childId) {
        Child child = childRepository.findById(childId).orElse(null);
        ChildLocation childLocation;
        if (locationDto.getId() != null) {
            childLocation = childLocationRepository.findById(locationDto.getId()).orElseThrow();
        } else if(child != null)
            childLocation = childLocationRepository.findByChild(child).orElse(new ChildLocation());
        else {
            childLocation = new ChildLocation();
        }
        childLocation.setStatus(locationDto.getStatus());
        childLocation.setName(locationDto.getName());
        childLocation.setTime(locationDto.getTime());
        childLocation.setCoordinate(locationDto.getCoordinate());
        childLocation.setLongitude(locationDto.getLongitude());
        childLocation.setLatitude(locationDto.getLatitude());
        childLocation.setChild(child);
        return childLocationRepository.save(childLocation);
    }

    public ChildLocationDto getChildLocationLatitude(Long childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        ChildLocation childLocation = childLocationRepository.findByChild(child).orElse(null);
        ChildLocationDto childLocationDto = new ChildLocationDto();
        if (childLocation != null) {
            childLocationDto.setLatitude(childLocation.getLatitude());
            childLocationDto.setLongitude(childLocation.getLongitude());
        }
        return childLocationDto;
    }

    public List<ChildLocationDto> getChildLocations(Long parentId) {
        List<ChildLocationDto> childLocationDtos = new ArrayList<>();
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        for (Child child: parent.getChildren()) {
            ChildLocationDto childLocationDto = new ChildLocationDto();
            ChildLocation childLocation = childLocationRepository.findByChild(child).orElse(null);
            if (childLocation != null) {
                childLocationDto.setLatitude(childLocation.getLatitude());
                childLocationDto.setLongitude(childLocation.getLongitude());
                childLocationDtos.add(childLocationDto);
            }
        }
        return childLocationDtos;
    }

    public List<ChildInformation> getAllChildByParent(Long parentId) {
        List<ChildInformation> childInformations = new ArrayList<>();
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        for (Child child: parent.getChildren()) {
            ChildInformation childInformation = new ChildInformation();
            ChildLocation childLocation = childLocationRepository.findByChild(child).orElse(null);
            if (childLocation != null) {
                childInformation.setChild(child);
                childInformation.setChildLocation(childLocation);
                childInformations.add(childInformation);
            }
        }
        return childInformations;
    }

    @Transactional
    public void deleteChild(Long childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        childLocationRepository.findByChild(child).ifPresent(childLocationRepository::delete);
        childRepository.delete(child);
    }

    @Transactional
    public Child deleteChildFromParent(Long childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        child.setParent(null);
        return childRepository.save(child);
    }

    @Transactional
    public void deleteParent(Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        for (Child child: parent.getChildren()) {
            deleteChild(child.getId());
        }
        parentRepository.delete(parent);
    }
}
