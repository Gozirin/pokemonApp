package Models

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)