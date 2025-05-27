
# Examen Microservices : Postes et Compétences

## Architecture Globale

Cette architecture microservices comprend les composants suivants :

1. **Service Discovery (Eureka Server)** - http://localhost:8762
2. **API Gateway (Spring Cloud Gateway)** - http://localhost:8882
3. **Config Service (Spring Cloud Config)** - http://localhost:8888
4. **Poste Service** - http://localhost:8081
5. **Compétence Service** - http://localhost:8080

## Prérequis

- Java 17+
- Maven 3.8+
- Git
- Docker (optionnel, pour le déploiement)

## Installation et Exécution

### 1. Cloner le dépôt

```bash
git clone https://github.com/MBDS-ANTENNES-24-25/projet-not-spring-microservices-JedidiahRC.git
```

### 2. Construire tous les modules

```bash
mvn clean install
```

### 3. Démarrer les services dans cet ordre

Dans des terminaux séparés :

```bash
# 1. Service Discovery
cd discovery
mvn spring-boot:run

# 2. Config Service
cd ../config
mvn spring-boot:run

# 3. API Gateway
cd ../gateway
mvn spring-boot:run

# 4. Poste Service
cd ../poste
mvn spring-boot:run

# 5. Compétence Service
cd ../competence
mvn spring-boot:run
```

## Endpoints des Services

Via l’API Gateway (`http://localhost:8882`)

### Poste Service (Bloquante***)

- `GET /api/postes` : Lister tous les postes
- `GET /api/postes/{id}` : Obtenir un poste par ID
- `POST /api/postes` : Créer un nouveau poste

### Compétence Service

- `GET /api/competences` : Lister toutes les compétences
- `GET /api/competences/{id}` : Obtenir une compétence par ID

## Endpoints d’Administration

- **Eureka Dashboard** : [http://localhost:8762](http://localhost:8761)
- **Config Server** : [http://localhost:8888](http://localhost:8888)
- **Actuator Health** : [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

## Tests avec Postman

Importer la collection située dans `docs/postman/Emploi_Microservices.postman_collection.json`

### Tests clés



**Récupération des compétences  :**

```http
GET /api/postes/DEV/competences
```

**Mise à jour à chaud de configuration :**

```http
POST /actuator/refresh
Headers: Content-Type: application/json
```

## Configuration Dynamique

1. Modifier les fichiers de configuration dans le dépôt Git.
2. Envoyer une requête `POST` à `/actuator/refresh` sur le service concerné.
3. Vérifier les changements avec `GET /actuator/configprops`.

## Déploiement avec Docker : Non realise


## Technologies Utilisées

- Spring Boot 3.x
- Spring Cloud (Gateway, Config, Eureka)
- Resilience4J
- Feign Client
- Lombok
- Actuator
- Docker (optionnel)

## Auteurs

Jedidiah Christy Rabemiarintsoa N:17
