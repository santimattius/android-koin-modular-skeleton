# Convention Plugins

The `plugins` folder defines project-specific convention plugins, used to keep a single
source of truth for common module configurations.

This approach is heavily based on
[https://developer.squareup.com/blog/herding-elephants/](https://developer.squareup.com/blog/herding-elephants/)
and
[https://github.com/jjohannes/idiomatic-gradle](https://github.com/jjohannes/idiomatic-gradle).

By setting up convention plugins in `plugins`, we can avoid duplicated build script setup,
messy `subproject` configurations, without the pitfalls of the `buildSrc` directory.

`plugins` is an included build, as configured in the root
[`settings.gradle.kts`](../settings.gradle.kts).

Inside `plugins` is a `convention` module, which defines a set of plugins that all normal
modules can use to configure themselves.

`plugins` also includes a set of `Kotlin` files used to share logic between plugins themselves,
which is most useful for configuring Android components (libraries vs applications) with shared
code.

These plugins are *additive* and *composable*, and try to only accomplish a single responsibility.
Modules can then pick and choose the configurations they need.
If there is one-off logic for a module without shared code, it's preferable to define that directly
in the module's `build.gradle`, as opposed to creating a convention plugin with module-specific
setup.

Current list of convention plugins:

- [`io.github.santimattius.android.application`](convention/src/main/kotlin/AndroidApplicationConventionPlugin.kt),
  [`io.github.santimattius.android.library`](convention/src/main/kotlin/AndroidLibraryConventionPlugin.kt),
  [`io.github.santimattius.android.test`](convention/src/main/kotlin/AndroidTestConventionPlugin.kt):
  Configures common Android and Kotlin options.
- [`io.github.santimattius.android.application.compose`](convention/src/main/kotlin/AndroidApplicationComposeConventionPlugin.kt),
  [`io.github.santimattius.android.library.compose`](convention/src/main/kotlin/AndroidLibraryComposeConventionPlugin.kt):
  Configures Jetpack Compose options
