package com.ml.truckingandconstructionwork.domain.interactor.registration

interface SaveDataInSharedInteractor {
    suspend operator fun invoke(userId:String)
}