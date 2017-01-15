# 說明
您好，我是蔡長恩，此為點餐系統中的管理者介面，我主要負責Firebase的資料庫、QRCode Zxing 的應用、FB API以及這App的UI畫面呈現。
# Firebase
Firebase的格式是JSON格式，上傳或更新資料都要使用MAP的儲存方式，點餐明細、菜色圖片、菜色價格、菜色名稱都是存放於Firebase內，菜色的圖片是儲存圖片的路徑，存放在google雲端內。
每次App都會先去讀取資料庫，先設定要查詢的類別名稱，再利用onDataChange去將資料一筆一筆撈取下來，再用Foreach的方式去儲存為ArrayList格式，再丟入Adapter內。
若是有新的資料或者修改資料，onDataChange會自動觸發，再去撈取資料

#QRCode Zxing 
使用QRCode的掃描功能，必須先開啟相機權限，再來開啟相機Camera.open()，並在onActivityResult中讀取掃到的QRCode的資料，會先在App中先判斷正確無誤後，再做點餐的動作，這部分是在使用者App當中使用。

#FB API
利用FB登入後，再利用callbackManager，讀回目前的狀態是否onSuccess，並利用profileTracker去讀取所要的資料，基本能取得資料為getID、getFirstName、getMiddleName、getLastName，若是要再取得其他資料，必須透過setReadPermissions詢問登入者是否同意給此資料權限，像是FB內的生日、E-mail、好友，若是想要獲得用戶大頭照，可以不需要權限只需要getID即可，使用http://graph.facebook.com/'userID'/picture?type=normal 即可顯示用戶大頭照
#UI
UI的呈現主要是Fragment分為三頁，SmartTabLayout為顯示上面三頁分頁，ViewPager能用呈現滑頁功能。
Fragment內使用RecyclerView去呈現，並設定Adapter，將從Firebase上讀取下來的ArrayList，丟入Adapter內做畫面的呈現。
使用TimerTask不斷的去執行notifyDataSetChanged，持續性的更新畫面。若有新的資料更新，則會自動觸發Firebase內的onDataChange，再去撈取資料後，更新畫面。

#使用者與管理者
此處為管理者的App部分，需與使用者搭配。
此為使用者github：https://github.com/leo0908w/blackmenu-master

#影片
此為在資策會中展示的影片 https://www.youtube.com/watch?v=dfVhyTedzsA&feature=youtu.be

#Thank you for your watching!
