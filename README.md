# Example Application to demonstrate shared element transition animation.

## Start an activity using the shared elements transition animation

Activity transitions in material design apps provide visual connections between different states through motion and transformations between common elements. You can specify custom animations for enter and exit transitions and for transitions of shared elements between activities.

A shared elements transition determines how views that are shared between two activities transition between these activities. For example, if two activities have the same image in different positions and sizes, the changeImageTransform shared element transition translates and scales the image smoothly between these activities

Android also supports these shared elements transitions:
1. changeBounds - Animates the changes in layout bounds of target views.
2. changeClipBounds - Animates the changes in clip bounds of target views.
3. changeTransform - Animates the changes in scale and rotation of target views.
4. changeImageTransform - Animates changes in size and scale of target images.

## License
    Copyright 2019 Dmytro Pashko

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
