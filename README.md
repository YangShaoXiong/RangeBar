## Preview
![效果图](https://github.com/YangShaoXiong/RangeBar/blob/master/screenshot/image.gif)
## Usage
```java
    <com.dreamer.library.RangeBar
        android:id="@+id/rangeBar"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:paddingLeft="10dp"
        android:paddingRight="10dp"
        app:range_background="#8a8a8a"
        app:range_num="5"
        app:thumb_color="#33475f"
        app:thumb_radius="10dp"
        app:vertical_line_height="5dp"
        app:vertical_line_width="5dp"
        app:horizontal_line_height="5dp"/>
```
## Attributes
| name                        |  format   | description               | default_value |
| :--------------------------:| :------:  | :-----------:             | :-----------: |
| range_background            | color     | RangeBar的背景颜色          | #8a8a8a       |
| range_num         | integer   | RangeBar中Range的数量             | 5 |
| thumb_color           | color     | RangeBar中thumb的背景颜色         | #33475f |
| thumb_radius        | dimension   | RangeBar中thumb的半径             | 10 |
| vertical_line_width | dimension   | RangeBar中Range的宽度 | 5 |
| vertical_line_height      | dimension   | RangeBar中Range的高度 | 5 |
| horizontal_line_height             | dimension | RangeBar的高度                | 5 |
## Thanks for
* 感谢[大恒]（https://github.com/Eternal-Zhai）同学指点实现点击事件

## License
```
   Copyright 2016 Dreamer

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```