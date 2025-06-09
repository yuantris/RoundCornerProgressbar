![Android Kotlin](https://img.shields.io/badge/Android-Kotlin-6C3FD1.svg?style=flat&logo=android)
![Minimum SDK Version](https://img.shields.io/badge/API-17+-brightgreen)

# RoundCornerProgressBar

Round corner is cool. Let's make your progress bar with round corner

![Round Corner Progress Bar Sample](/image/header.jpg)

Colorful rounded corner progress bar

# Download

## Gradle

```groovy
implementation 'io.core.widget:progressbar:1.0.0'
```

# Feature

### Standard Features

- Primary progress and secondary progress supported
- Primary progress, secondary progress and progress background color are customizable
- Customize your own progress background padding
- Customize your own progress's corner radius
- Reversing progress bar supported
- Progress bar with gradient color? Yes!
- Progress change animation? Absolutely yes!

### Component Features

- Progress expanding from center with `CenteredRoundCornerProgressBar`
- Heading icon supported with `IconRoundCornerProgressBar`
- Text inside progress supported with `TextRoundCornerProgressBar`
- Indeterminate animation supported with `IndeterminateRoundCornerProgressBar` or `IndeterminateCenteredRoundCornerProgressBar`

# Usage

For using custom attribute of progress bar, define 'app' namespace as root view attribute in your layout

```xml
xmlns:app="http://schemas.android.com/apk/res-auto"
```

## RoundCornerProgressBar

### Example

```xml
<com.ffgreatking.progressbar.IconRoundCornerProgressBar
    android:layout_width="260dp"
    android:layout_height="30dp"
    app:fyBackgroundColor="#0A000000"
    app:fyBackgroundPadding="2dp"
    app:fyMax="100"
    app:fyProgress="40"
    app:fyProgressColor="#EF5350"
    app:fyRadius="10dp"
    app:fySecondaryProgress="60"
    app:fySecondaryProgressColor="#40EF5350" />
```

### Layout XML

```xml
<com.ffgreatking.progressbar.RoundCornerProgressBar
    app:fyProgress="float"
    app:fySecondaryProgress="float"
    app:fyMax="float"
    app:fyRadius="dimension"
    app:fyBackgroundPadding="dimension"
    app:fyReverse="boolean"
    app:fyProgressColor="color"
    app:fySecondaryProgressColor="color"
    app:fyBackgroundColor="color"
    app:fyAnimationEnable="boolean"
    app:fyAnimationSpeedScale="float" />
```

### Public Methods

```kotlin
// Progress
fun getMax(): Float
fun setMax(max: Float)
fun setMax(max: Int)
fun getProgress(): Float
fun setProgress(progress: Int)
fun setProgress(progress: Float)
fun getSecondaryProgress(): Float
fun setSecondaryProgress(secondaryProgress: Int)
fun setSecondaryProgress(secondaryProgress: Float)

// Dimension
fun getRadius(): Int
fun setRadius(radius: Int)
fun getPadding(): Int
fun setPadding(padding: Int)
fun getLayoutWidth(): Float

// Animation
fun enableAnimation()
fun disableAnimation()
fun getAnimationSpeedScale(): Float
fun setAnimationSpeedScale(scale: Float)
fun isProgressAnimating(): Boolean
fun isSecondaryProgressAnimating(): Boolean

// Reversing Progress
fun isReverse(): Boolean
fun setReverse(isReverse: Boolean)

// Color
fun getProgressBackgroundColor(): Int
fun setProgressBackgroundColor(color: Int)
fun getProgressColor(): Int
fun setProgressColor(color: Int)
fun getProgressColors(): List<Int>
fun setProgressColors(colors: List<Int>)
fun getSecondaryProgressColor(): Int
fun setSecondaryProgressColor(color: Int)
fun getSecondaryProgressColors(): List<Int>
fun setSecondaryProgressColors(colors: List<Int>)

// Listener
fun setOnProgressChangedListener(listener: OnProgressChangedListener)
```

## CenteredRoundCornerProgressBar

Same as RoundCornerProgressBar but reversing does not supported.

### Example

```xml
<com.ffgreatking.progressbar.CenteredRoundCornerProgressBar
    android:layout_width="260dp"
    android:layout_height="30dp"
    app:fyBackgroundColor="#0A000000"
    app:fyBackgroundPadding="2dp"
    app:fyMax="100"
    app:fyProgress="40"
    app:fyProgressColor="#EF5350"
    app:fyRadius="10dp"/>
```

### Layout XML

```xml
<com.ffgreatking.progressbar.CenteredRoundCornerProgressBar
    app:fyProgress="float"
    app:fySecondaryProgress="float"
    app:fyMax="float"
    app:fyRadius="dimension"
    app:fyBackgroundPadding="dimension"
    app:fyProgressColor="color"
    app:fySecondaryProgressColor="color"
    app:fyBackgroundColor="color"
    app:fyAnimationEnable="boolean"
    app:fyAnimationSpeedScale="float" />
```

### Public Methods

```kotlin
// Progress
fun getMax(): Float
fun setMax(max: Float)
fun setMax(max: Int)
fun getProgress(): Float
fun setProgress(progress: Int)
fun setProgress(progress: Float)
fun getSecondaryProgress(): Float
fun setSecondaryProgress(secondaryProgress: Int)
fun setSecondaryProgress(secondaryProgress: Float)

// Dimension
fun getRadius(): Int
fun setRadius(radius: Int)
fun getPadding(): Int
fun setPadding(padding: Int)
fun getLayoutWidth(): Float

// Animation
fun enableAnimation()
fun disableAnimation()
fun getAnimationSpeedScale(): Float
fun setAnimationSpeedScale(scale: Float)
fun isProgressAnimating(): Boolean
fun isSecondaryProgressAnimating(): Boolean

// Color
fun getProgressBackgroundColor(): Int
fun setProgressBackgroundColor(color: Int)
fun getProgressColor(): Int
fun setProgressColor(color: Int)
fun getProgressColors(): List<Int>
fun setProgressColors(colors: List<Int>)
fun getSecondaryProgressColor(): Int
fun setSecondaryProgressColor(color: Int)
fun getSecondaryProgressColors(): List<Int>
fun setSecondaryProgressColors(colors: List<Int>)

// Listener
fun setOnProgressChangedListener(listener: OnProgressChangedListener)
```

## IconRoundCornerProgressBar

Icon size is required for this progress bar. Use `wrap_content` for `layout_height` is recommended.

```xml
<com.ffgreatking.progressbar.IconRoundCornerProgressBar
        android:layout_height="wrap_content"
        app:fyIconSize="40dp"
        ... />
```

### Example

```xml
<com.ffgreatking.progressbar.IconRoundCornerProgressBar
    android:layout_width="260dp"
    android:layout_height="wrap_content"
    app:fyBackgroundColor="#0A000000"
    app:fyBackgroundPadding="2dp"
    app:fyIconBackgroundColor="#00796B"
    app:fyIconPadding="5dp"
    app:fyIconSize="40dp"
    app:fyIconSrc="@drawable/ic_android"
    app:fyMax="150"
    app:fyProgress="90"
    app:fyProgressColor="#EF5350"
    app:fyRadius="5dp"
    app:fyReverse="true" />
```

### Layout XML

```xml
<com.ffgreatking.progressbar.IconRoundCornerProgressBar
        app:fyProgress="float"
        app:fySecondaryProgress="float"
        app:fyMax="float"
        app:fyRadius="dimension"
        app:fyBackgroundPadding="dimension"
        app:fyReverse="boolean"
        app:fyProgressColor="color"
        app:fySecondaryProgressColor="color"
        app:fyBackgroundColor="color"
        app:fyAnimationEnable="boolean"
        app:fyAnimationSpeedScale="float"
        app:fyIconSrc="reference"
        app:fyIconSize="dimension"
        app:fyIconWidth="dimension"
        app:fyIconHeight="dimension"
        app:fyIconPadding="dimension"
        app:fyIconPaddingLeft="dimension"
        app:fyIconPaddingRight="dimension"
        app:fyIconPaddingTop="dimension"
        app:fyIconPaddingBottom="dimension"
        app:fyIconBackgroundColor="color" />
```

### Public Methods

```kotlin
// Progress
fun getMax(): Float
fun setMax(max: Float)
fun setMax(max: Int)
fun getProgress(): Float
fun setProgress(progress: Int)
fun setProgress(progress: Float)
fun getSecondaryProgress(): Float
fun setSecondaryProgress(secondaryProgress: Int)
fun setSecondaryProgress(secondaryProgress: Float)

// Dimension
fun getRadius(): Int
fun setRadius(radius: Int)
fun getPadding(): Int
fun setPadding(padding: Int)
fun getLayoutWidth(): Float

fun getIconSize(): Int
fun setIconSize(size: Int)
fun getIconPadding(): Int
fun setIconPadding(padding: Int)
fun getIconPaddingLeft(): Int
fun setIconPaddingLeft(padding: Int)
fun getIconPaddingTop(): Int
fun setIconPaddingTop(padding: Int)
fun getIconPaddingRight(): Int
fun setIconPaddingRight(padding: Int)
fun getIconPaddingBottom(): Int
fun setIconPaddingBottom(padding: Int)

// Animation
fun enableAnimation()
fun disableAnimation()
fun getAnimationSpeedScale(): Float
fun setAnimationSpeedScale(scale: Float)
fun isProgressAnimating(): Boolean
fun isSecondaryProgressAnimating(): Boolean

// Reversing Progress
fun isReverse(): Boolean
fun setReverse(isReverse: Boolean)

// Color
fun getProgressBackgroundColor(): Int
fun setProgressBackgroundColor(color: Int)
fun getProgressColor(): Int
fun setProgressColor(color: Int)
fun getProgressColors(): List<Int>
fun setProgressColors(colors: List<Int>)
fun getSecondaryProgressColor(): Int
fun setSecondaryProgressColor(color: Int)
fun getSecondaryProgressColors(): List<Int>
fun setSecondaryProgressColors(colors: List<Int>)
fun getColorIconBackground(): Int
fun setIconBackgroundColor(color: Int)

// Icon
fun getIconImageResource(): Int
fun setIconImageResource(resId: Int)
fun getIconImageBitmap(): Birmap
fun setIconImageBitmap(bitmap: Bitmap)
fun getIconImageDrawable(): Drawable
fun setIconImageDrawable(drawable: Drawable)

// Listener
fun setOnProgressChangedListener(listener: OnProgressChangedListener)
fun setOnIconClickListener(listener: OnIconClickListener)
```

## TextRoundCornerProgressBar

### Example

```xml
<com.ffgreatking.progressbar.TextRoundCornerProgressBar
    android:layout_width="260dp"
    android:layout_height="30dp"
    app:fyBackgroundColor="#0A000000"
    app:fyBackgroundPadding="2dp"
    app:fyMax="100"
    app:fyProgress="40"
    app:fyProgressColor="#EF5350"
    app:fyRadius="80dp"
    app:fyReverse="true"
    app:fySecondaryProgress="60"
    app:fySecondaryProgressColor="#40009688"
    app:fyTextPositionPriority="outside"
    app:fyTextProgress="40"
    app:fyTextProgressColor="#111111" />
```

### Layout XML

```xml
<com.ffgreatking.progressbar.TextRoundCornerProgressBar
        app:fyProgress="float"
        app:fySecondaryProgress="float"
        app:fyMax="float"
        app:fyRadius="dimension"
        app:fyBackgroundPadding="dimension"
        app:fyReverse="boolean"
        app:fyProgressColor="color"
        app:fySecondaryProgressColor="color"
        app:fyBackgroundColor="color"
        app:fyAnimationEnable="boolean"
        app:fyAnimationSpeedScale="float"
        app:fyTextProgressColor="color"
        app:fyTextProgressSize="dimension"
        app:fyTextProgressMargin="dimension"
        app:fyTextProgress="String"
        app:fyTextInsideGravity="start|end"
        app:fyTextOutsideGravity="start|end"
        app:fyTextPositionPriority="inside|outside" />
```

### Public Methods

```kotlin
// Progress
fun getMax(): Float
fun setMax(max: Float)
fun setMax(max: Int)
fun getProgress(): Float
fun setProgress(progress: Int)
fun setProgress(progress: Float)
fun getSecondaryProgress(): Float
fun setSecondaryProgress(secondaryProgress: Int)
fun setSecondaryProgress(secondaryProgress: Float)

// Dimension
fun getRadius(): Int
fun setRadius(radius: Int)
fun getPadding(): Int
fun setPadding(padding: Int)
fun getLayoutWidth(): Float
fun getTextProgressSize(): Int
fun setTextProgressSize(size: Int)
fun getTextProgressMargin(): Int
fun setTextProgressMargin(margin: Int)

// Animation
fun enableAnimation()
fun disableAnimation()
fun getAnimationSpeedScale(): Float
fun setAnimationSpeedScale(scale: Float)
fun isProgressAnimating(): Boolean
fun isSecondaryProgressAnimating(): Boolean

// Reversing Progress
fun isReverse(): Boolean
fun setReverse(isReverse: Boolean)

// Color
fun getProgressBackgroundColor(): Int
fun setProgressBackgroundColor(color: Int)
fun getProgressColor(): Int
fun setProgressColor(color: Int)
fun getProgressColors(): List<Int>
fun setProgressColors(colors: List<Int>)
fun getSecondaryProgressColor(): Int
fun setSecondaryProgressColor(color: Int)
fun getSecondaryProgressColors(): List<Int>
fun setSecondaryProgressColors(colors: List<Int>)
fun getTextProgressColor(): Int
fun setTextProgressColor(color: Int)

// Text
fun getProgressText(): String
fun setProgressText(text: String)

// Position
fun getTextPositionPriority(): Int
fun setTextPositionPriority(priority: Int)
fun getTextInsideGravity(): Int
fun setTextInsideGravity(gravity: Int)
fun getTextOutsideGravity(): Int
fun setTextOutsideGravity(gravity: Int)

// Listener
fun setOnProgressChangedListener(listener: OnProgressChangedListener)
```

## IndeterminateRoundCornerProgressBar

### Example

```xml
<com.ffgreatking.progressbar.indeterminate.IndeterminateRoundCornerProgressBar
    android:layout_width="260dp"
    android:layout_height="10dp"
    app:fyAnimationSpeedScale="3"
    app:fyBackgroundColor="#0A000000"
    app:fyProgressColor="#EF5350" />
```

### Layout XML

```xml
<com.ffgreatking.progressbar.indeterminate.IndeterminateRoundCornerProgressBar
        app:fyRadius="dimension"
        app:fyBackgroundPadding="dimension"
        app:fyReverse="boolean"
        app:fyProgressColor="color"
        app:fySecondaryProgressColor="color"
        app:fyBackgroundColor="color"
        app:fyAnimationSpeedScale="float" />
```

### Public Methods

```kotlin
// Dimension
fun getRadius(): Int
fun setRadius(radius: Int)
fun getPadding(): Int
fun setPadding(padding: Int)
fun getLayoutWidth(): Float

// Animation
fun getAnimationSpeedScale(): Float
fun setAnimationSpeedScale(scale: Float)

// Reversing Progress
fun isReverse(): Boolean
fun setReverse(isReverse: Boolean)

// Color
fun getProgressBackgroundColor(): Int
fun setProgressBackgroundColor(color: Int)
fun getProgressColor(): Int
fun setProgressColor(color: Int)
fun getProgressColors(): List<Int>
fun setProgressColors(colors: List<Int>)
fun getSecondaryProgressColor(): Int
fun setSecondaryProgressColor(color: Int)
fun getSecondaryProgressColors(): List<Int>
fun setSecondaryProgressColors(colors: List<Int>)
```

## IndeterminateCenteredRoundCornerProgressBar

Same as IndeterminateRoundCornerProgressBar

### Example

```xml
<com.ffgreatking.progressbar.indeterminate.IndeterminateCenteredRoundCornerProgressBar
    android:layout_width="260dp"
    android:layout_height="10dp"
    app:fyAnimationSpeedScale="0.75"
    app:fyBackgroundColor="#0A000000"
    app:fyProgressColor="#EF5350" />
```

### Layout XML

```xml
<com.ffgreatking.progressbar.IndeterminateCenteredRoundCornerProgressBar
        app:fyRadius="dimension"
        app:fyBackgroundPadding="dimension"
        app:fyReverse="boolean"
        app:fyProgressColor="color"
        app:fySecondaryProgressColor="color"
        app:fyBackgroundColor="color"
        app:fyAnimationSpeedScale="float" />
```

### Public Methods

```kotlin
// Dimension
fun getRadius(): Int
fun setRadius(radius: Int)
fun getPadding(): Int
fun setPadding(padding: Int)
fun getLayoutWidth(): Float

// Animation
fun getAnimationSpeedScale(): Float
fun setAnimationSpeedScale(scale: Float)

// Reversing Progress
fun isReverse(): Boolean
fun setReverse(isReverse: Boolean)

// Color
fun getProgressBackgroundColor(): Int
fun setProgressBackgroundColor(color: Int)
fun getProgressColor(): Int
fun setProgressColor(color: Int)
fun getProgressColors(): List<Int>
fun setProgressColors(colors: List<Int>)
fun getSecondaryProgressColor(): Int
fun setSecondaryProgressColor(color: Int)
fun getSecondaryProgressColors(): List<Int>
fun setSecondaryProgressColors(colors: List<Int>)
```

## Apply Gradient Progress Bar Color

Gradient color for progress bar must be in int array resource. At least 2 colors.

```xml
<!-- Color Resource -->
<resources>
    <array name="sample_progress_gradient">
        <item>#009688</item>
        <item>#80CBC4</item>
    </array>
</resources>

<!-- Layout -->
<com.ffgreatking.progressbar.RoundCornerProgressBar
    ...
    app:fyBackgroundColor="#0A000000"
    app:fyBackgroundPadding="4dp"
    app:fyMax="100"
    app:fyProgress="50"
    app:fyProgressColors="@array/sample_progress_gradient"
    app:fyRadius="30dp" />
```

Progress bar does not clipped when size changed. So the gradient color will fully display without clipping also.

## Apply Progress Change Animation

Animation when progress change is disabled by default (exclude `IndeterminateProgressBar` and `IndeterminateCenteredProgressBar`).

So you have to enable the animation by XML attribute or programmatically

```xml
<com.ffgreatking.progressbar.RoundCornerProgressBar
    ...
    app:fyAnimationEnable="true"
    app:fyAnimationSpeedScale="1" />

```

When progress changed, the animation will applied automatically.

Animation speed scale's value is float between 0.2 - 5.0 (default is 1.0). Higher for slow down the animation, lower for speed up.

# Licence

Copyright 2025 Akexorcist

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
