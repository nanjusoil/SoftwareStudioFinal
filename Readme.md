#[3D的部分](https://github.com/nanjusoil/LibGdx_3d_test)
+ 使用libgdx
+ 先build gradle之後 在run configuration設定asset path就可以跑


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

#Item介紹
+ 分四種Type: CONTROL, TOOL, MESSAGE, FURNITURE
+ CONTROL:控制遊戲的進行ex:切換房間的左右鍵,桌子(因為可以切換背景)
+ TOOL:在房間裡撿到，可以在接下來的逃脫過程中派上用場ex:key
+ MESSAGE:ex:房間裡撿到的紙團，攤開後會有提示
+ FURNITURE: ex: 保險箱,圖畫，不能被移動

#遊戲流程
+ TOOL
+ 撿到Item(道具)/call ItemBox的putinItem()
+ (換到正確的場景)
+ 在道具欄點一下道具(代表拿起道具)/call ItemBox的checkItem()
+ 在正確的位置點一下，道具就會發揮作用並消失/call ItemBox的useItem()
+ solControlP5就是玩家在拿起道具後該點的位置

+ MESSAGE
+ 撿到紙條/call ItemBox的putinItem()
+ 在道具欄點一下道具可以查看它的內容/call ItemBox的checkItem()
+ 當達到特定條件時，紙條就會消失(ex:保險箱打開後，寫有保險箱密碼的紙條就會消失)/call ItemBox的useItem()

#音樂播放
+ 首先先宣告一個 ------ Clip 變數名稱
+ 再利用 變數名稱 = MusicPlay.getMusic("src/" + path + "Sounds/音樂檔名稱.wav");  <<< 注意一定要加上"src/"!!!!!     path為  "main/resources/" >>>
+ 要暫停，撥放，循環播放分別用 變數名稱.stop();
				變數名稱.start();
				變數名稱.loop(Clip.LOOP_CONTINUOUSLY);
+ 音樂檔僅能為.wav檔，附上音樂剪輯及轉檔網址 ----   音樂剪輯:: http://mp3cut.net/
													轉檔::  http://media.io/zh/
													
