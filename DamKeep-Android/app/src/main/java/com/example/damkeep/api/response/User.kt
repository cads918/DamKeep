package com.example.damkeep.api.response

import java.util.*

data class User (
    var username : String,
    var fullName : String,
    var roles : String,
    var id : UUID
)