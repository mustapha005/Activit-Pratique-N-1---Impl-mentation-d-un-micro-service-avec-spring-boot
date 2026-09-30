# Activité pratique n° 1 — Implémentation d'un microservice avec Spring Boot

Projet maintenu par **Mustapha Aarab** — [mostaphaaarabe663@gmail.com](mailto:mostaphaaarabe663@gmail.com).

Ce microservice gère des comptes bancaires et expose leurs données avec une API REST, Spring Data REST et GraphQL. Les comptes sont stockés dans une base H2 en mémoire. Le code Java utilise le package `com.aarab.bankaccountservice`.

## Technologies

- Java 21 et Spring Boot 4.1.1.
- Spring Web MVC, Spring Data JPA et Spring Data REST.
- Spring for GraphQL, H2 et Lombok.
- Maven Wrapper fourni dans le dépôt.

## Démarrage

Configurer `JAVA_HOME` vers un JDK 21, puis lancer depuis la racine du projet :

```powershell
./mvnw.cmd clean verify
./mvnw.cmd spring-boot:run
```

Sous Linux ou macOS, utiliser `./mvnw` à la place de `./mvnw.cmd`. Avec Maven déjà installé, les commandes équivalentes sont `mvn clean verify` et `mvn spring-boot:run`.

L'application démarre sur le port **8080** et crée onze comptes de démonstration avec des soldes aléatoires en MAD. Les données sont recréées à chaque démarrage.

## API REST

| Méthode | Route | Opération |
| --- | --- | --- |
| `GET` | `/api/bankAccounts` | Lister les comptes |
| `GET` | `/api/bankAccounts/{id}` | Consulter un compte |
| `POST` | `/api/bankAccounts` | Créer un compte |
| `PUT` | `/api/bankAccounts/{id}` | Modifier un compte |
| `DELETE` | `/api/bankAccounts/{id}` | Supprimer un compte |

Exemple de corps JSON pour une création :

```json
{
  "balance": 1500.0,
  "currency": "MAD",
  "accountType": "CURRENT_ACCOUNT"
}
```

Les types disponibles sont `CURRENT_ACCOUNT` et `SAVING_ACCOUNT`.

Spring Data REST expose également le dépôt à `/bankAccounts`, une recherche à `/bankAccounts/search/byType?t=CURRENT_ACCOUNT` et la projection `p1` à `/bankAccounts?projection=p1`.

## GraphQL

L'API reçoit les requêtes à `/graphql`. L'interface GraphiQL est disponible à <http://localhost:8080/graphiql>.

```graphql
query {
  accountsList {
    id
    balance
    currency
    accountType
  }
}
```

```graphql
mutation {
  addAccount(bankAccount: {
    balance: 1500
    currency: "MAD"
    accountType: "CURRENT_ACCOUNT"
  }) {
    id
    balance
    currency
    accountType
  }
}
```

Le schéma propose aussi `bankAccountById(id: ...)`, `updateAccount(id: ..., bankAccount: ...)` et `deleteAccount(id: ...)`. Il se trouve dans `src/main/resources/graphql/schema.graphqls`.

## Organisation

| Package | Contenu |
| --- | --- |
| `entities` et `enums` | Entités JPA, types de comptes et projection |
| `repositories` | Accès aux comptes avec Spring Data JPA |
| `dtos` et `mappers` | Objets de requête/réponse et conversion |
| `service` | Création et modification des comptes |
| `web` | Contrôleurs REST et GraphQL |
| `exceptions` | Résolveur d'erreurs GraphQL fourni dans le projet |

La base utilise l'URL `jdbc:h2:mem:account-db`. Sa console est accessible à `/h2-console` pendant l'exécution de l'application.

Il s'agit d'une application de démonstration, avec une base temporaire et des points d'accès sans authentification.
