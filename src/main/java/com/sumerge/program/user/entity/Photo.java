package com.sumerge.program.user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PHOTO", schema = "STAFF")
@NamedQueries({
        @NamedQuery(name = "Photo.findAll", query = "SELECT e FROM Photo e")
})
public class Photo implements Serializable {

    @Id
    @Column(name = "PHOTOID", nullable = false)
    private int photoId;

    @Column(name = "IMAGENAME")
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "EMPID", nullable = false)
    private Employee employee;

    public Photo() {
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
