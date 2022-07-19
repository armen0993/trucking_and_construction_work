package com.ml.truckingandconstructionwork.domain.interactor.log_in

interface SetSkippedTypeInSharedInteractor {
    suspend operator fun invoke(boolean: Boolean)
}