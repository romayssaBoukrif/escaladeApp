<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="header.jsp"%>

<div class="row justify-content-center">
    <div class="col-md-8 col-lg-5">
        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <div class="text-center mb-4">
                    <h1 class="fw-bold mb-1">Connexion</h1>
                    <p class="text-muted mb-0">
                        Connectez-vous pour accéder à votre espace membre.
                    </p>
                </div>

                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        Email ou mot de passe incorrect.
                    </div>
                </c:if>

                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        Vous avez été déconnecté(e).
                    </div>
                </c:if>

                <form method="post" action="/login">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input name="username" class="form-control" type="text" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mot de passe</label>
                        <input name="password" class="form-control" type="password" />
                    </div>

                    <div class="d-grid mb-3">
                        <button class="btn btn-primary" type="submit">Se connecter</button>
                    </div>
                </form>

                <div class="text-center">
                    <a href="/forgot-password" class="text-decoration-none">
                        Mot de passe oublié ?
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>