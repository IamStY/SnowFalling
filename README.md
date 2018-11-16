# SnowFalling
Note : This is my first library project on github , so im not really familiar  with the rules
Please notice me if I did something improper, or maybe bad coding-style . 

Many thanks!

SnowFalling  effect for android


![snowing](https://cloud.githubusercontent.com/assets/14084447/20951162/2b67fbcc-bc5f-11e6-90bc-6fe8c02d6253.gif)



first of all , in your project gradle
add this -
-------------------------------------------------------------------------------------------
		allprojects {
		repositories {
			..
			maven { url 'https://jitpack.io' }
		}
	}
-------------------------------------------------------------------------------------------
	
then go to your app's dependencies
add this -
-------------------------------------------------------------
	  compile 'com.github.IamStY:SnowFalling:v1.4'
-------------------------------------------------------------------------------------------
I've made some default definition for some variable, so all you need to do is type the following code In your activity -


-----------------------------------------------------------------------------------

	   SnowFlakesLayout snowFlakesLayout;
	   snowFlakesLayout = (SnowFlakesLayout) this.findViewById(R.id.snowflakelayout);
	   snowFlakesLayout.init();
	   snowFlakesLayout.startSnowing();
   --------------------------------------------------------------------------------

   and xml -
   
     <com.example.stevenyang.snowfalling.SnowFlakesLayout
        android:id="@+id/snowflakelayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></com.example.stevenyang.snowfalling.SnowFlakesLayout>
		
--------------------------------------------------------------------------------
Or you can do this

        snowFlakesLayout.init();
        snowFlakesLayout.setWholeAnimateTiming(3000000);
        snowFlakesLayout.setAnimateDuration(10000);
        snowFlakesLayout.setGenerateSnowTiming(300);
        snowFlakesLayout.setRandomSnowSizeRange(40, 1);
        snowFlakesLayout.setImageResourceID(R.drawable.your_snow_flake_pic);
        snowFlakesLayout.setEnableRandomCurving(true);
        snowFlakesLayout.setEnableAlphaFade(true);
        snowFlakesLayout.startSnowing();
		
---------------------------------------------------------------------------------





Peace!
                             Yang Cheng-Kuang