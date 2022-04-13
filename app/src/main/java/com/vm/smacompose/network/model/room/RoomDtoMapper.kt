package com.vm.smacompose.network.model.room
import com.vm.smacompose.domain.model.Room
import com.vm.smacompose.domain.util.DomainMapper
class RoomDtoMapper : DomainMapper<RoomDto, Room> {
    override fun mapToDomainModel(model: RoomDto): Room {
        return Room(
            id = model.id,
            createdAt = model.createdAt,
            isOccupied = model.isOccupied,
            maxOccupancy = model.maxOccupancy
        )
    }

    override fun mapFromDomainModel(domainModel: Room): RoomDto {
        return RoomDto(
            id = domainModel.id,
            createdAt = domainModel.createdAt,
            isOccupied = domainModel.isOccupied,
            maxOccupancy = domainModel.maxOccupancy
        )
    }
    fun toDomainList(initial: List<RoomDto>): List<Room>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Room>): List<RoomDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}