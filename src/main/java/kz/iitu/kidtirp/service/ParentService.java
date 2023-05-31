package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.exceptions.ObjectNotFoundException;
import kz.iitu.kidtirp.model.dto.request.*;
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

    @Transactional
    public Parent updateParent(ParentRequest request) {
        Parent parent = parentRepository.findById(request.getId()).orElseThrow();
        parent.setCvv(request.getCvv());
        parent.setNumberCard(request.getNumberCard());
        parent.setCardExpireDate(request.getCardExpireDate());
        parent.setOwnerNameCard(request.getOwnerNameCard());
        return parentRepository.save(parent);
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

    public Parent getParentById(Long id) {
        return parentRepository.findById(id).orElseThrow();
    }

    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    public Child getChildById(Long id) {
        return childRepository.findById(id).orElseThrow();
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
        Child child = childRepository.findById(childId).orElseThrow();
        ChildLocation childLocation = new ChildLocation();
        if (locationDto.getId() != null) {
            childLocation = childLocationRepository.findById(locationDto.getId()).orElseThrow();
        } else {
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

    public List<ChildInformation> getAllChildByParent(Long parentId) {
        List<ChildInformation> childInformations = new ArrayList<>();
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        for (Child child: parent.getChildren()) {
            ChildInformation childInformation = new ChildInformation();
            ChildLocation childLocation = childLocationRepository.findByChild(child);
            childInformation.setChild(child);
            childInformation.setChildLocation(childLocation);
            childInformations.add(childInformation);
        }
        return childInformations;
    }

}
