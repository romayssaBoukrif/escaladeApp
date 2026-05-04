<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="row mb-4">
    <div class="col-lg-8">
        <h1 class="fw-bold mb-1">Recherche de sorties</h1>
        <p class="text-muted mb-0">
            Recherchez une sortie par nom et par catégorie.
        </p>
    </div>
</div>

<div class="card shadow-sm border-0 mb-4">
    <div class="card-body">
        <form method="get" action="/search" class="row g-3 align-items-end">
            <div class="col-md-5">
                <label class="form-label">Nom</label>
                <input type="text"
                       name="nom"
                       class="form-control"
                       value="${nom}" />
            </div>

            <div class="col-md-5">
                <label class="form-label">Catégorie</label>
                <select name="categoryId" class="form-select">
                    <option value="">-- Toutes les catégories --</option>
                    <c:forEach var="categorie" items="${categories}">
                        <option value="${categorie.id}"
                                <c:if test="${selectedCategoryId == categorie.id}">selected</c:if>>
                                ${categorie.nom}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-2 d-grid">
                <button type="submit" class="btn btn-primary">Chercher</button>
            </div>
        </form>
    </div>
</div>

<c:choose>
    <c:when test="${empty resultats}">
        <div class="alert alert-info">
            Aucun résultat trouvé.
        </div>
    </c:when>
    <c:otherwise>
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="h4 mb-0">Résultats</h2>
            <span class="badge text-bg-secondary">${resultats.size()} sortie(s)</span>
        </div>

        <div class="row g-4">
            <c:forEach var="sortie" items="${resultats}">
                <div class="col-md-6 col-lg-4">
                    <div class="card shadow-sm border-0 h-100">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title mb-2">${sortie.nom}</h5>

                            <p class="mb-2">
                                <strong>Date :</strong> ${sortie.dateSortie}
                            </p>

                            <p class="mb-2">
                                <strong>Catégorie :</strong> ${sortie.category.nom}
                            </p>

                            <p class="text-muted mb-3">
                                <c:choose>
                                    <c:when test="${not empty sortie.description}">
                                        ${sortie.description}
                                    </c:when>
                                    <c:otherwise>
                                        Aucune description.
                                    </c:otherwise>
                                </c:choose>
                            </p>

                            <c:if test="${pageContext.request.userPrincipal != null}">
                                <p class="mb-2">
                                    <strong>Site web :</strong>
                                    <c:choose>
                                        <c:when test="${not empty sortie.siteWeb}">
                                            <a href="${sortie.siteWeb}" target="_blank">${sortie.siteWeb}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">Non renseigné</span>
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <p class="mb-3">
                                    <strong>Créateur :</strong>
                                        ${sortie.createur.prenom} ${sortie.createur.nom}
                                </p>
                            </c:if>

                            <div class="mt-auto">
                                <a href="/sorties/${sortie.id}" class="btn btn-outline-primary btn-sm">
                                    Voir le détail
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<%@include file="footer.jsp"%>