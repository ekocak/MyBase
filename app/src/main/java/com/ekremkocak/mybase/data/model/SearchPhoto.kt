/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ekremkocak.mybase.data.model

import com.google.gson.annotations.SerializedName
import com.google.samples.apps.sunflower.data.SearchUser
import com.google.samples.apps.sunflower.data.SearchPhotoUrls

/**
 * Data class that represents a photo from Unsplash.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://unsplash.com/documentation#get-a-photo).
 */
data class SearchPhoto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: SearchPhotoUrls,
    @field:SerializedName("user") val user: SearchUser
)
