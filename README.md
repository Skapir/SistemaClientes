# SistemaClientes

Sistema de Ventas de Escritorio desarrollado en **Java (NetBeans)** con conexión a **MySQL**, orientado a pequeños negocios. Permite gestionar productos, clientes, ventas, generación de boletas en PDF y exportación de reportes a Excel.

## 🚀 Tecnologías utilizadas

- Java SE 8
- NetBeans 12.6+
- MySQL 8.0
- JDBC
- Apache POI (Excel)
- iText (PDF)

## 📦 Módulos principales

- Mantenimiento de productos (CRUD)
- Gestión de clientes y proveedores
- Registro de ventas
- Generación de boletas de venta en PDF
- Exportación de reportes a Excel
- Autenticación básica por usuario

## 📂 Estructura del proyecto

```
SistemaClientes/
├── src/              # Código fuente Java
├── nbproject/        # Configuración NetBeans
├── lib/              # Librerías externas (.jar)
├── SQL/              # Script de base de datos
├── .gitignore
└── README.md
```

## ⚙️ Configuración

1. Clonar el repositorio:

```bash
git clone https://github.com/Skapir/SistemaClientes.git
```

2. Importar en NetBeans como proyecto existente.
3. Ejecutar el script `estructura_base.sql` en MySQL Workbench.
4. Editar `ConexionBD.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/sistema_clientes";
private static final String USER = "root";
private static final String PASSWORD = "tu_contraseña";
```

## 👤 Contacto

Desarrollado por **Skapir (Sergio P.)**  
📧 skapir.dev@gmail.com
