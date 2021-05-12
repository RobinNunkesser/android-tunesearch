plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("de.hshl.isd:explicitarchitecture-tunesearch-core-ports:0.0.3")
    implementation("de.hshl.isd:explicitarchitecture:0.1.0")
    testImplementation("junit:junit:4.+")
}