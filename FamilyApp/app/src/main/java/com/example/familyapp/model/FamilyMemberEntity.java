package com.example.familyapp.model;

import androidx.room.Entity;

@Entity(tableName = "family_members")
public class FamilyMemberEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public String relationship;
    public String birthday;
    public String avatarUri;
    public long lastInteractionTime;
}
