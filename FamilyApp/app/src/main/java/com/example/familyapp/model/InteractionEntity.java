package com.example.familyapp.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "interactions",
        foreignKeys = @
                ForeignKey(entity = FamilyMemberEntity.class,
                parentColumns = "id",
                childColumns = "familyMemberId",
                onDelete = CASCADE))
public class InteractionEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long familyMemberId;
    public String type;        // call, meet, gift
    public String note;
    public String imageUri;
    public long date;
}
