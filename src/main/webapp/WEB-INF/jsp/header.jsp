<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Club Escalade</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="/">Club Escalade</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNavbar">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/categories">Catégories</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/search">Recherche</a>
                </li>

                <c:if test="${pageContext.request.userPrincipal != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/membre/mes-sorties">Mes sorties</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/membre/sorties/new">Créer une sortie</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/membres">Membres</a>
                    </li>
                </c:if>
            </ul>

            <div class="d-flex align-items-center gap-3">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal != null}">
                        <span class="text-white">
                            Connecté : ${pageContext.request.userPrincipal.name}
                        </span>

                        <form method="post" action="/logout" class="m-0">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-outline-light">
                                Se déconnecter
                            </button>
                        </form>
                    </c:when>

                    <c:otherwise>
                        <a href="/login" class="btn btn-outline-light">
                            Se connecter
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</nav>

<main class="container py-4">