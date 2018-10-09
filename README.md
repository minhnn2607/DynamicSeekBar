# Dynamic Seekbar
Custom SeekBar with your thumb, progress bar and popup info...
![DynamicSeekbar](https://i.makeagif.com/media/11-06-2017/iUUFZF.gif)

## Setup
Add dependency to your __build.gradle__

```groovy		
maven {
  url "https://dl.bintray.com/moninc/maven"
}	
```	
```groovy		
compile 'vn.n2m.dynamic_seekbar:DynamicSeekBar:1.0:@aar'
```	
## Usage
Add __DynamicSeekBarView__ to your layout

```groovy	
 <vn.nms.dynamic_seekbar.DynamicSeekBarView
        android:id="@+id/dynamicSeekbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:dsbv_progressColor="@color/yellow"                  //Set progress color
        app:dsbv_progressBackgroundColor="@color/gray_bold"     //Set progress background color
        app:dsbv_infoTextSize="12dp"                            //Set text size popup info
        app:dsbv_infoTextColor="@android:color/white"           //Set text color popup info
        app:dsbv_infoBackgroundColor="@color/yellow"            //Set background color popup info
        app:dsbv_infoRadius="30"                                //Set background corner radius popup info
        app:dsbv_max="100"                                      //Set max value of seekbar 
        app:dsbv_progress="50"                                  //Init progress value of seekbar
        app:dsbv_thumbDrawable="@drawable/ic_sun"               //Set thumb drawable
        app:dsbv_infoInitText="@string/init_info_text"          //Init popup info text
	/>       
        
        dsbv_isHideInfo="false/true"                            //Show/hide popup info
        dsbv_progressDrawable="@drawable/progress_drawable"     //Set progress drawable
```

## Develop By
Minh Nguyen
        
## License
```
Copyright 2017 Minh Nguyen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

          
        
