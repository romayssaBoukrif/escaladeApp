<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="row justify-content-center">
    <div class="col-md-8 col-lg-5">
        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <div class="text-center mb-4">
                    <h1 class="fw-bold mb-1">Mot de passe oublié</h1>
                    <p class="text-muted mb-0">
                        Saisissez votre adresse email pour recevoir un lien de réinitialisation.
                    </p>
                </div>

                <c:if test="${not empty message}">
                    <div class="alert alert-success">
                            ${message}
                    </div>
                </c:if>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                            ${error}
                    </div>
                </c:if>

                <form method="post" action="/forgot-password">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <div class="mb-3">
                        <label class="form-label">Adresse email</label>
                        <input name="email"
                               class="form-control"
                               type="email"
                               value="${email}" />
                    </div>

                    <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-primary">
                            Envoyer le lien
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