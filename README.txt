Mini-projekt 2 - Mateusz Oleksik (s29325)

Wykonano: 
=> Aplikacje 1, która tworzy intent rozgłoszeniowy o nowym produkcie [2 pkt]
=> Aplikacje 2, która rejestruje BroadcastReceiver, który przy przechwyceniu uruchamia serwis notyfikacji, 
który tworzy powiadomienie [8 pkt]
=> BroadcastReceiver egzekwuje uprawnienia [1 pkt]

Należy uruchomić aplikacje 2 i ją zminimalizować, BroadcastReceiver jest rejestrowaniu przy jej uruchomieniu
-------------------------------------
Aplikacja 1 - tworzenie i wysyłanie intentu rozgłoszeniowego:

W klasie CreateShoppingItemActivity metoda sendNewItemBroadcast(item: ShoppingItem, itemId: Int) tworzy i rozgłaśnia
Intent rozgłoszeniowy po stworzeniu i dodaniu do bazy danych nowego produktu.

-------------------------------------
Aplikacja 2 - rejestracja BroadcastReceiver, uruchamianie serwisu, tworzenie powiadomienia:

Rejestracja BroadcastReceiver => metoda registerReceiver() w klasie MainActivity
Uruchamianie serwisu powiadomień => metoda onReceive(p0: Context?, p1: Intent?) w klasie ShoppingItemCreationReceiver 
Tworzenie powiadomień => metoda sendNotification(intent: Intent) w klasie NotificationService

Link do repozytorium (w razie problemów z zip): https://github.com/MattOlecs/SMB_1