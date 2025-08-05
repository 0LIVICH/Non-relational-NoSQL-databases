#!/bin/bash

# Примеры тестирования API для Spring Boot MongoDB User Application
# Убедитесь, что приложение запущено на http://localhost:8080

echo "=== Тестирование Spring Boot MongoDB User API ==="
echo ""

# Проверка здоровья API
echo "1. Проверка здоровья API:"
curl -X GET http://localhost:8080/api/users/health
echo ""
echo ""

# Создание пользователей
echo "2. Создание пользователей:"
echo "Создание первого пользователя:"
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Иван Иванов",
    "email": "ivan@example.com",
    "age": 25
  }'
echo ""
echo ""

echo "Создание второго пользователя:"
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Мария Петрова",
    "email": "maria@example.com",
    "age": 30
  }'
echo ""
echo ""

echo "Создание третьего пользователя:"
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Алексей Сидоров",
    "email": "alex@example.com",
    "age": 28
  }'
echo ""
echo ""

# Получение всех пользователей
echo "3. Получение всех пользователей:"
curl -X GET http://localhost:8080/api/users
echo ""
echo ""

# Поиск пользователей
echo "4. Поиск пользователей по имени:"
curl -X GET http://localhost:8080/api/users/search/name/Иван
echo ""
echo ""

echo "5. Поиск пользователей по возрасту:"
curl -X GET http://localhost:8080/api/users/search/age/25
echo ""
echo ""

echo "6. Поиск пользователей старше 25 лет:"
curl -X GET http://localhost:8080/api/users/search/age/gt/25
echo ""
echo ""

echo "7. Поиск пользователей в диапазоне возрастов (25-30):"
curl -X GET "http://localhost:8080/api/users/search/age/range?min=25&max=30"
echo ""
echo ""

echo "8. Поиск пользователей по части имени:"
curl -X GET http://localhost:8080/api/users/search/name/contains/Иван
echo ""
echo ""

# Получение пользователя по email
echo "9. Получение пользователя по email:"
curl -X GET http://localhost:8080/api/users/email/ivan@example.com
echo ""
echo ""

echo "=== Тестирование завершено ==="
echo ""
echo "Для тестирования обновления и удаления пользователей:"
echo "1. Сначала получите ID пользователя из списка выше"
echo "2. Затем используйте следующие команды:"
echo ""
echo "# Обновление пользователя (замените {id} на реальный ID):"
echo "curl -X PUT http://localhost:8080/api/users/{id} \\"
echo "  -H \"Content-Type: application/json\" \\"
echo "  -d '{"
echo "    \"name\": \"Иван Петров\","
echo "    \"email\": \"ivan.petrov@example.com\","
echo "    \"age\": 26"
echo "  }'"
echo ""
echo "# Удаление пользователя (замените {id} на реальный ID):"
echo "curl -X DELETE http://localhost:8080/api/users/{id}" 