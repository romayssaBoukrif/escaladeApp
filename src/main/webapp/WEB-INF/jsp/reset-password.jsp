<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="row justify-content-center">
    <div class="col-md-8 col-lg-5">
        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <div class="text-center mb-4">
                    <h1 class="fw-bold mb-1">Nouveau mot de passe</h1>
                    <p class="text-muted mb-0">
                        Saisissez votre nouveau mot de passe pour finaliser la réinitialisation.
                    </p>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                            ${error}
                    </div>
                </c:if>

                <form method="post" action="/reset-password">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" name="token" value="${token}" />

                    <div class="mb-3">
                        <label class="form-label">Nouveau mot de passe</label>
                        <input name="newPassword"
                               class="form-control"
                               type="password" />
                    </div>

                    <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-primary">
                            Réinitialiser le mot de passe
                        </button>
                    </div>
                </form>

                <div class="text-center">
                    <a href="/login" class="text-decoration-none">
                        Retour à la connexion
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>