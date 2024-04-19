package com.ekremkocak.mybase.data.network.response

import com.google.gson.annotations.SerializedName

data class Result(@SerializedName("userTypeId")
                  val userTypeId: Int = 0,
                  @SerializedName("usersSports")
                  val usersSports: List<Int>?,
                  @SerializedName("trainerSports")
                  val trainerSports: List<Int>?,
                  @SerializedName("surName")
                  val surName: String = "",
                  @SerializedName("phone")
                  val phone: String = "",
                  @SerializedName("name")
                  val name: String = "",
                  @SerializedName("about")
                  val about: String = "",
                  @SerializedName("genderTypeId")
                  val genderTypeId: Int = 0,
                  @SerializedName("birthDate")
                  val birthDate: String = "",
                  @SerializedName("email")
                  val email: String = "")