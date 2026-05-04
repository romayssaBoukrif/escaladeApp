<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="fw-bold mb-1">${membre.prenom} ${membre.nom}</h1>
        <p class="text-muted mb-0">
            Sorties proposées par ce membre.
        </p>
    </div>

    <a href="/membres" class="btn btn-outline-secondary">
        Retour à la liste des membres
    </a>
</div>

<div class="mb-4">
    <p><strong>Email :</strong> ${membre.email}</p>
</div>

<c:choose>
    <c:when test="${empty membre.sorties}">
        <div class="alert alert-info">
            Ce membre n'a proposé aucune sortie.
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
                            <th>Date</th>
                            <th>Catégorie</th>
                            <th>Description</th>
                            <th class="text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="sortie" items="${membre.sorties}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td><strong>${sortie.nom}</strong></td>
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
                                <td class="text-center">
                                    <a href="/sorties/${sortie.id}" class="btn btn-outline-primary btn-sm">
                                        Voir le détail
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