# [Coding Styles](https://google.github.io/styleguide/javaguide.html)
+ 照著一定的Coding Style應該會看起來比較好一點

# [ControlP5 -- a proccesing library](http://www.sojamo.de/libraries/controlP5/)
+ 請照加入core.jar的模式加入ControlP5.jar
+ 這個jar有很多內建的方法可以用，我目前是用來做按鈕(core.jar沒有按鈕)

# 新Applet
+ 現在的流程是 Main(建立JFrame)->建立MainApplet 按右鍵 ->建立LoginApplet(測試Applet的轉換)
+ 以後應該會是 Main -> LoginApplet ->每個場景一個Applet

# 如何建立新的Applet
+ 					loginapplet.init();  //一定要初始化
+					loginapplet.start();
+					loginapplet.setFocusable(true);
+                   jframe.setContentPane(loginapplet); //把Applet加到JFrame