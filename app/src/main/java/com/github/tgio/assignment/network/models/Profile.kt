package com.github.tgio.assignment.network.models

class Profile(
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val country: String
) {
    override fun toString(): String {
        return "Profile(id='$id', firstName='$firstName', lastName='$lastName', age=$age, gender='$gender', country='$country')"
    }
}