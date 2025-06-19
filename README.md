### **VisyLogger Plugin Features / Возможности плагина VisyLogger**

#### **English Version** 🇬🇧
**Core Features**:
1. **Diamond & Rare Items Mining Logger**  
   - Tracks player mining of diamonds, ancient debris, netherite scraps (configurable in `config.yml`)  
   - Detects suspicious mining speeds (e.g., 64 diamonds in 5 minutes)  

2. **Operator Alerts**  
   - Private notifications in chat for server operators only  
   - Customizable alert messages in `config.yml`  

3. **Player Statistics**  
   - Command `/logger <player>` to view mining stats  
   - Shows item counts and timeframes  

4. **Customization**  
   - Edit `tracked-items` and thresholds in `config.yml`  
   - Supports any block type from Minecraft  

5. **Compatibility**  
   - Built for **PaperMC 1.21.3**  
   - Requires Java 17  

---

#### **Русская Версия** 🇷🇺
**Основные функции**:
1. **Логирование добычи алмазов и редких предметов**  
   - Отслеживает добычу алмазов, древних обломков, обломков незерита (настраивается в `config.yml`)  
   - Фиксирует подозрительную скорость (например, 64 алмаза за 5 минут)  

2. **Уведомления для операторов**  
   - Приватные сообщения в чат только для операторов сервера  
   - Настраиваемые тексты уведомлений в `config.yml`  

3. **Статистика игроков**  
   - Команда `/logger <ник>` для просмотра статистики  
   - Показывает количество добытых предметов и временные промежутки  

4. **Настройка**  
   - Редактируемый список отслеживаемых предметов в `config.yml`  
   - Поддержка любых блоков из Minecraft  

5. **Совместимость**  
   - Работает на **PaperMC 1.21.3**  
   - Требуется Java 17  

---

### **Commands / Команды**
| English | Русский | Description / Описание |
|---------|---------|------------------------|
| `/logger` | `/logger` | Show your mining stats / Показать свою статистику |
| `/logger <player>` | `/logger <ник>` | View another player's stats / Посмотреть статистику другого игрока |

---

### **Config Examples / Примеры конфигурации**
**`config.yml` (English)**:
```yaml
tracked-items:
  - "DIAMOND"
  - "ANCIENT_DEBRIS"

limits:
  max-items: 64
  time-interval: 300  # 5 minutes in seconds

messages:
  alert: "&c[ALERT] &fPlayer &e{nick} &fmined &a{count} &fof &b{item} &fin &e{time} sec!"
```

**`config.yml` (Русский)**:
```yaml
tracked-items:
  - "DIAMOND"
  - "ANCIENT_DEBRIS"

limits:
  max-items: 64
  time-interval: 300  # 5 минут в секундах

messages:
  alert: "&c[ALERT] &fИгрок &e{nick} &fдобыл &a{count} &fпредметов &b{item} &fза &e{time} сек!"
```

---

### **Need Help? / Нужна помощь?**
- **English**: Contact me with `/logger` command issues or config errors!  
- **Русский**: Пишите, если есть проблемы с командой `/logger` или настройками!
