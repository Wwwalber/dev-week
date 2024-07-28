package dio.dev_week.domain.model;

import jakarta.persistence.Entity;

// using DRY since we don't want to repeat code
@Entity(name ="tb_feature")
public class Feature extends BaseItem {

}
