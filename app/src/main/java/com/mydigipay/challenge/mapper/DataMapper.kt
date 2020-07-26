package com.mydigipay.challenge.mapper

abstract class DataMapper<M, E> {

    abstract fun transformToEntity(model: M): E?

    abstract fun transformToModel(entity: E): M?

    fun transformToEntities(models: List<M>): List<E> {
        val entities: MutableList<E> = mutableListOf()
        for (model in models) {
            transformToEntity(model)?.let { entities.add(it) }
        }

        return entities
    }

    fun transformToModels(entities: List<E>): List<M> {
        val models: MutableList<M> = mutableListOf()
        for (entity in entities) {
            transformToModel(entity)?.let { models.add(it) }
        }

        return models
    }
}
