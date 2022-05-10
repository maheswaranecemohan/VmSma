package com.vm.smacompose.network.model.people

import com.vm.smacompose.domain.model.People
import com.vm.smacompose.domain.util.DomainMapper
import com.vm.smacompose.network.model.PeopleDto

class PeopleDtoMapper : DomainMapper<PeopleDto, People> {
    override fun mapToDomainModel(model: PeopleDto): People {
        return People(
            avatar = model.avatar,
            createdAt = model.createdAt,
            email = model.email,
            favouriteColor = model.favouriteColor,
            firstName = model.firstName,
            id = model.id,
            jobtitle = model.jobtitle,
            lastName = model.lastName
        )
    }

    override fun mapFromDomainModel(domainModel: People): PeopleDto {
        return PeopleDto(
            avatar = domainModel.avatar,
            createdAt = domainModel.createdAt,
            email = domainModel.email,
            favouriteColor = domainModel.favouriteColor,
            firstName = domainModel.firstName,
            id = domainModel.id,
            jobtitle = domainModel.jobtitle,
            lastName = domainModel.lastName
        )
    }

    fun toDomainList(initial: List<PeopleDto>): List<People>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<People>): List<PeopleDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}