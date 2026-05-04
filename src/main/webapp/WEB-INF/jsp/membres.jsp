<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="d-flex justify-content-between align-items-center mb-4">
    <div>
        <h1 class="fw-bold mb-1">Membres</h1>
        <p class="text-muted mb-0">
            Consultez les membres du club et les sorties qu’ils proposent.
        </p>
    </div>
</div>

<c:choose>
    <c:when test="${empty membres}">
        <div class="alert alert-info">
            Aucun membre n'est disponible.
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
                            <th>Prénom</th>
                            <th>Email</th>
                            <th class="text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="membre" items="${membres}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${membre.nom}</td>
                                <td>${membre.prenom}</td>
                                <td>${membre.email}</td>
                                <td class="text-center">
                                    <a href="/membres/${membre.id}" class="btn btn-outline-primary btn-sm">
                                        Voir ses sorties
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