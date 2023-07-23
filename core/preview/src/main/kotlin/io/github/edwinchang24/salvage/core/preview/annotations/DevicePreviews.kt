package io.github.edwinchang24.salvage.core.preview.annotations

import androidx.compose.ui.tooling.preview.Preview

const val Devices = "Devices"

@Preview(name = "Phone", group = Devices, device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Preview(name = "Landscape", group = Devices, device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "Foldable", group = Devices, device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
@Preview(name = "Tablet", group = Devices, device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
annotation class DevicePreviews
