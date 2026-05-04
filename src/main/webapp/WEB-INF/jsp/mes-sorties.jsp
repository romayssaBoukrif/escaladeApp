<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="mb-1">Mes sorties</h1>
        <p class="text-muted mb-0">Gérez vos sorties depuis votre espace membre.</p>
    </div>

</div>

<!-- Petit résumé dashboard -->
<div class="row mb-4">
    <div class="col-md-4">
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <h6 class="text-muted">Nombre total de sorties</h6>
                <h3 class="mb-0">${sorties.size()}</h3>
            </div>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${empty sorties}">
        <div class="alert alert-info">
            Vous n'avez encore créé aucune sortie.
        </div>
    </c:when>
    <c:otherwise>
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th>Nom</th>
                            <th>Date</th>
                            <th>Catégorie</th>
                            <th>Description</th>
                            <th>Site web</th>
                            <th class="text-center">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="sortie" items="${sorties}">
                            <tr>
                                <td>
                                    <strong>${sortie.nom}</strong>
                                </td>
                                <td>${sortie.dateSortie}</td>
                                <td>${sortie.category.nom}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty sortie.description}">
                                            ${sortie.description}
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">Aucune description</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty sortie.siteWeb}">
                                           ${sortie.siteWeb}
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">—</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="text-center">
                                    <div class="d-flex justify-content-center gap-2">
                                        <a href="/sorties/${sortie.id}" class="btn btn-outline-primary btn-sm" title="Détail">
                                            <i class="bi bi-eye"></i>
                                        </a>

                                        <a href="/membre/sorties/${sortie.id}/edit" class="btn btn-warning btn-sm" title="Modifier">
                                            <i class="bi bi-pencil-square"></i>
                                        </a>

                                        <form method="post" action="/membre/sorties/${sortie.id}/delete" class="m-0">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button type="submit" class="btn btn-danger btn-sm" title="Supprimer">
                                                <i class="bi bi-trash"></i>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<%@include file="footer.jsp"%>