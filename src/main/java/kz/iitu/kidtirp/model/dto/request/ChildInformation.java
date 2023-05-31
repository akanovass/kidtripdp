package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.Child;
import kz.iitu.kidtirp.model.entity.ChildLocation;
import lombok.Data;

@Data
public class ChildInformation {
    private ChildLocation childLocation;
    private Child child;
}
