package de.hshl.isd.tunesearch.common

interface InputBoundary<T : Request> {
    fun send(request: T,outputBoundary: OutputBoundary);
}