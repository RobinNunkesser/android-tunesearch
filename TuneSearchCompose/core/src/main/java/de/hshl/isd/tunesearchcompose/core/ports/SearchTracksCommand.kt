package de.hshl.isd.tunesearchcompose.core.ports

import de.hshl.isd.explicitarchitecture.Command

interface SearchTracksCommand : Command<SearchTracksDTO,List<CollectionEntity>> {
}