<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="row justify-content-center">
    <div class="col-lg-8">
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <h1 class="mb-4">${pageTitle}</h1>

                <form:form method="post" action="${formAction}" modelAttribute="sortieForm">
                    <div class="mb-3">
                        <label class="form-label">Nom</label>
                        <form:input path="nom" cssClass="form-control"/>
                        <form:errors path="nom" cssClass="text-danger small d-block mt-1"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <form:textarea path="description" cssClass="form-control" rows="4"/>
                        <form:errors path="description" cssClass="text-danger small d-block mt-1"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Site web</label>
                        <form:input path="siteWeb" cssClass="form-control"/>
                        <form:errors path="siteWeb" cssClass="text-danger small d-block mt-1"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Date</label>
                        <form:input path="dateSortie" type="date" cssClass="form-control"/>
                        <form:errors path="dateSortie" cssClass="text-danger small d-block mt-1"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Catégorie</label>
                        <form:select path="categoryId" cssClass="form-select">
                            <form:option value="" label="-- Choisir une catégorie --"/>
                            <c:forEach var="categorie" items="${categories}">
                                <form:option value="${categorie.id}" label="${categorie.nom}"/>
                            </c:forEach>
                        </form:select>
                        <form:errors path="categoryId" cssClass="text-danger small d-block mt-1"/>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">${submitLabel}</button>
                        <a href="/membre/mes-sorties" class="btn btn-secondary">Annuler</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>