# winterkit


# Gradle Dependency

### Repository
The Gradle dependency is available via [jitpack.io](https://jitpack.io/#mensarb/winterkit).

The minimum API level supported by this library is API 19.

Step 1.Add the JitPack repository to your build file
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```gradle
dependencies {
	// ... other dependencies here
    compile 'com.github.mensarb:winterkit:{version}'
}
```
