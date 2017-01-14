# 說明
您好，我是蔡長恩，此為點餐系統中的管理者介面，我主要負責Firebase的資料庫、QRCode Zxing 的應用、FB的第三方登入以及這App的UI畫面呈現。
# Firebase
Firebase的格式是JSON格式，上傳或更新資料都要使用MAP的儲存方式，點餐明細、菜色圖片、菜色價格、菜色名稱都是存放於Firebase內，菜色的圖片是儲存圖片的路徑，存放在google雲端內。
每次App都會先去讀取資料庫，先設定要查詢的類別名稱，再利用onDataChange去將資料一筆一筆撈取下來。
若是有新的資料或者修改資料，onDataChange會自動觸發，再去撈取資料

#QRCode Zxing 
使用QRCode的掃描功能，
