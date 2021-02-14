package com.hun2.server.domain.repository

import com.hun2.server.domain.entity.vacation.Vacation
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*

interface VacationRepository: JpaRepository<Vacation, Long> {
    fun findByIdAndUserId(id: Long, userId: Long): Vacation?
    fun findAllByUserIdAndStartDateBetween(userId: Long, from: LocalDate, to: LocalDate): List<Vacation>

    // 테스트용 레파지토리
    @Suppress("UNCHECKED_CAST")
    class Fake: VacationRepository, JpaRepository<Vacation, Long> {

        private val vacationStorage = mutableListOf<Vacation>()

        override fun findByIdAndUserId(id: Long, userId: Long): Vacation? {
            return vacationStorage.firstOrNull { it.userId == userId }
        }

        override fun findAllByUserIdAndStartDateBetween(userId: Long, from: LocalDate, to: LocalDate): List<Vacation> {
            val year = from.year
            return vacationStorage.filter { it.userId == userId && it.startDate.year == year }
        }

        override fun <S : Vacation?> save(entity: S): S {
            vacationStorage.add(entity as Vacation)
            return entity
        }

        override fun <S : Vacation?> saveAll(entities: MutableIterable<S>): MutableList<S> {

            entities.forEach {
                vacationStorage.add(it as Vacation)
            }

            return vacationStorage.map { it as S }.toMutableList()
        }

        override fun findById(id: Long): Optional<Vacation> {
            TODO("Not yet implemented")
        }

        override fun existsById(id: Long): Boolean {
            TODO("Not yet implemented")
        }

        override fun findAll(): MutableList<Vacation> {
            TODO("Not yet implemented")
        }

        override fun findAll(sort: Sort): MutableList<Vacation> {
            TODO("Not yet implemented")
        }

        override fun <S : Vacation?> findAll(example: Example<S>): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun <S : Vacation?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun findAll(pageable: Pageable): Page<Vacation> {
            TODO("Not yet implemented")
        }

        override fun <S : Vacation?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
            TODO("Not yet implemented")
        }

        override fun findAllById(ids: MutableIterable<Long>): MutableList<Vacation> {
            TODO("Not yet implemented")
        }

        override fun count(): Long {
            TODO("Not yet implemented")
        }

        override fun <S : Vacation?> count(example: Example<S>): Long {
            TODO("Not yet implemented")
        }

        override fun deleteById(id: Long) {
            TODO("Not yet implemented")
        }

        override fun delete(entity: Vacation) {
            TODO("Not yet implemented")
        }

        override fun deleteAll(entities: MutableIterable<Vacation>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll() {
            TODO("Not yet implemented")
        }

        override fun <S : Vacation?> findOne(example: Example<S>): Optional<S> {
            TODO("Not yet implemented")
        }

        override fun <S : Vacation?> exists(example: Example<S>): Boolean {
            TODO("Not yet implemented")
        }

        override fun flush() {
            TODO("Not yet implemented")
        }

        override fun <S : Vacation?> saveAndFlush(entity: S): S {
            TODO("Not yet implemented")
        }

        override fun deleteInBatch(entities: MutableIterable<Vacation>) {
            TODO("Not yet implemented")
        }

        override fun deleteAllInBatch() {
            TODO("Not yet implemented")
        }

        override fun getOne(id: Long): Vacation {
            TODO("Not yet implemented")
        }
    }
}