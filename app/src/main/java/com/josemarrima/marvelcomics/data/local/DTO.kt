package com.josemarrima.marvelcomics.data.local

import com.josemarrima.marvelcomics.model.MarvelResponse

/**
 * Convert Network results to domain objects
 */
fun MarvelResponse.asDomainModel(): List<Comic> {
    return data.results.map {
        Comic(
            id = it.id,
            title = it.title,
            description = it.description,
            url = "${it.thumbnail.path}.${it.thumbnail.extension}")
    }
}