package com.example.user.model.entity

import com.example.user.model.profile.Gender
import org.springframework.data.annotation.CreatedDate
import java.io.Serializable
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity
@Table(name = "USER_PROFILE")
class UserProfile(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var surname: String = "",

    @Column(nullable = false)
    var cid: String = "",

    @Column(nullable = true)
    var address: String? = null,

    @Enumerated(EnumType.STRING)
    var gender: Gender = Gender.MALE,

    @Column(nullable = true)
    var phoneNumber: String? = null,

    @Column(nullable = true)
    var email: String? = null,

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    var createdTimestamp: Date = Date()
) : Serializable