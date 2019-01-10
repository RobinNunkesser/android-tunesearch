package de.hshl.isd.tunesearch.common

interface Presenter<Entity, ViewModel> {
    fun present(entity: Entity): ViewModel
}





