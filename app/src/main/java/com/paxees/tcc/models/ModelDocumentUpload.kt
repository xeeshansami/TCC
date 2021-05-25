package com.paxees.tcc.models



enum class DocumentCategoryState {
    Mandatory, MandatoryCompleted, Optional, OptionalCompleted
}

class ModelDocumentUpload(
    val category: String,
    var state: DocumentCategoryState,
    val images: ArrayList<ImageModel>
)