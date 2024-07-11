<div style="text-align: center;">
    <h1 align="center" style="font-size: 30px;">CustomProgressbar Library</h1>
</div>
A circular faded style progress bar for Android, offering customization for color, thickness, and size. The user can start and pause the progress bar, and configure parameters directly in XML.


<div align="center">
    <b>
	    Select between 3 Sizes: MIN | MED | MAX</b>
    <br>
    <img width="600" alt="progressSizes" src="https://github.com/ShalevShar/progressbarLib/assets/127881894/f957151a-4c5d-4a5d-90c8-4c88d91f7368">
    <br>
    <b>
	    Thickness Control:</b>
    <br>
    <img width="600" alt="progressThickness" src="https://github.com/ShalevShar/progressbarLib/assets/127881894/454a19b7-c1ab-4d61-9919-2ab0e42680da">
    <br>
    <b>
	    Define ANY Color:</b>
    <br>
    <img width="400" alt="progressColor" src="https://github.com/ShalevShar/progressbarLib/assets/127881894/b0dabb25-8205-4598-bdb8-3020bc75b7b6">

</div>



________________________________________________________________________

 ### Customizable circular progress bar with start and pause functionality.


![E7C94F09-9A90-446F-812A-4460C2FAA21A-ezgif com-resize](https://github.com/ShalevShar/progressbarLib/assets/127881894/e5d5f4eb-06a9-4b71-88d8-24fa4a7de984)



## Setup
Add the dependency inside build.gradle:
```
dependencies {
		implementation(project(":progressbarLib"))
}
```

## Usage
-*XML Layout Example:
```java
 <com.example.progressbarlib.CustomProgressbar
        android:id="@+id/main_progressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:progressStrokeWidth="10dp"
        app:progressColor="@color/aqua_blue"
        app:progressSize="min" />
```
## Activity Implementation Example:
```java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.progressbarlib.CustomProgressbar;

public class MainActivity extends AppCompatActivity {
    private CustomProgressbar main_progressBar;
    private Button main_BTN_stop_start_Progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_progressBar = findViewById(R.id.main_progressBar);
        main_BTN_stop_start_Progressbar = findViewById(R.id.main_BTN_stop_start_Progressbar);

        main_progressBar.setColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
        main_progressBar.setStrokeWidth(20);
        main_progressBar.setSize(CustomProgressbar.Size.MAX);
        main_progressBar.start();

        main_BTN_stop_start_Progressbar.setOnClickListener(v -> {
            if (main_progressBar.isRunning()) {
                main_progressBar.pause();
            } else {
                main_progressBar.start();
            }
        });
    }
}
```



## Credits

-----

## License

-----

Enjoy using CustomProgressbar for your Android projects!
