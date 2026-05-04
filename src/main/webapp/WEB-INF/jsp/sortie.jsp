<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="row justify-content-center">
    <div class="col-lg-8">
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <h1 class="card-title mb-4">${sortie.nom}</h1>

                <p class="mb-3">
                    <strong>Description :</strong>
                    <c:choose>
                        <c:when test="${not empty sortie.description}">
                            ${sortie.description}
                        </c:when>
                        <c:otherwise>
                            <span class="text-muted">Aucune description.</span>
                        </c:otherwise>
                    </c:choose>
                </p>

                <p class="mb-3">
                    <strong>Date :</strong> ${sortie.dateSortie}
                </p>

                <p class="mb-3">
                    <strong>Catégorie :</strong> ${sortie.category.nom}
                </p>

                <c:if test="${authenticated}">
                    <p class="mb-3">
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
                        <a href="/membres/${sortie.createur.id}">
                                ${sortie.createur.prenom} ${sortie.createur.nom}
                        </a>
                    </p>
                </c:if>


            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>