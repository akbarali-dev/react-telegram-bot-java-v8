package uz.akbarali.foodappjavav8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.akbarali.foodappjavav8.model.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "attachment_contents")
public class AttachmentContent extends AbsEntity {
    private byte[] data;
    @OneToOne
    private Attachment attachment;
}
