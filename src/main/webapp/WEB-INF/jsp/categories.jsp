<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="fw-bold mb-1">Catégories</h1>
        <p class="text-muted mb-0">Parcourez les catégories de sorties du club.</p>
    </div>

    <c:if test="${pageContext.request.userPrincipal != null}">
        <a href="/membre/sorties/new" class="btn btn-primary">Créer une sortie</a>
    </c:if>
</div>

<c:choose>
    <c:when test="${empty categories}">
        <div class="alert alert-info">
            Aucune catégorie n'est disponible pour le moment.
        </div>
    </c:when>
    <c:otherwise>
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th></th>
                            <th>Nom de la catégorie</th>
                            <th>Description</th>
                            <th class="text-center"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="category" items="${categories}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>
                                    <strong>${category.nom}</strong>
                                </td>
                                <td class="text-muted">
                                    Découvrez les sorties liées à cette catégorie.
                                </td>
                                <td class="text-center">
                                    <a href="/categories/${category.id}" class="btn btn-outline-primary btn-sm">
                                        Voir les sorties
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