<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="fw-bold mb-1">${category.nom}</h1>
        <p class="text-muted mb-0">
            Consultez les sorties disponibles dans cette catégorie.
        </p>
    </div>

    <a href="/categories" class="btn btn-outline-secondary">
        <i class="bi bi-arrow-left"></i>
    </a>
</div>

<c:choose>
    <c:when test="${empty category.sorties}">
        <div class="alert alert-info">
            Aucune sortie n'est disponible dans cette catégorie.
        </div>
    </c:when>
    <c:otherwise>
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Nom</th>
                            <th>Catégorie</th>
                            <th>Date</th>
                            <th>Description</th>

                            <c:if test="${pageContext.request.userPrincipal != null}">
                                <th>Site web</th>
                                <th>Créateur</th>
                            </c:if>

                            <th class="text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="sortie" items="${category.sorties}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>

                                <td>
                                    <strong>${sortie.nom}</strong>
                                </td>

                                <td>${sortie.category.nom}</td>

                                <td class="text-nowrap">${sortie.dateSortie}</td>

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

                                <c:if test="${pageContext.request.userPrincipal != null}">
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty sortie.siteWeb}">
                                                <a href="${sortie.siteWeb}" target="_blank"
                                                   class="btn btn-outline-secondary btn-sm"
                                                   title="Ouvrir le site web">
                                                    <i class="bi bi-box-arrow-up-right"></i>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-muted">—</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>
                                            ${sortie.createur.prenom} ${sortie.createur.nom}
                                    </td>
                                </c:if>

                                <td class="text-center">
                                    <a href="/sorties/${sortie.id}"
                                       class="btn btn-outline-primary btn-sm"
                                       title="Voir le détail">
                                        <i class="bi bi-eye"></i>
                                    </a>
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